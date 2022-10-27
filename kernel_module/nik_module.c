#include <linux/kernel.h> 
#include <linux/init.h> 
#include <linux/module.h> 
#include <linux/kdev_t.h> 
#include <linux/fs.h> 
#include <linux/cdev.h> 
#include <linux/device.h> 
#include <linux/slab.h>                 // kmalloc() 
#include <linux/uaccess.h>              // copy_to/from_user() 
#include <linux/proc_fs.h> 
 
#include <linux/pid.h> 
#include <linux/sched.h> 
#include <linux/sched/signal.h> 
#include <linux/netdevice.h> 
#include <linux/device.h>
#include <linux/memblock.h>
 
#define BUF_SIZE 1024 

MODULE_LICENSE("Dual BSD/GPL"); 
MODULE_DESCRIPTION("lab2 osi"); 
MODULE_VERSION("1.0");

static int pid = 1; 
static int struct_id = 1; 
static unsigned long adress = 0;
 
static struct proc_dir_entry *parent;

static int      __init lab_driver_init(void); 
static void     __exit lab_driver_exit(void);

/***************** Procfs Functions *******************/ 
static int      open_proc(struct inode *inode, struct file *file); 
static int      release_proc(struct inode *inode, struct file *file); 
static ssize_t  read_proc(struct file *filp, char __user *buffer, size_t 
length,loff_t * offset); 
static ssize_t  write_proc(struct file *filp, const char *buff, size_t len, 
loff_t * off); 
 
/* 
** procfs operation sturcture 
*/ 
static struct file_operations proc_fops = { 
        .open = open_proc,  
        .read = read_proc, 
        .write = write_proc, 
        .release = release_proc 
};


// page

static struct page *get_my_page(struct mm_struct* mm,
				unsigned long address) {
	

    pgd_t *pgd = pgd_offset(mm, address);
    if (pgd_none(*pgd) || pgd_bad(*pgd)) {
        return NULL;
    }

    p4d_t *p4d = p4d_offset(pgd, address);
    if (p4d_none(*p4d) || p4d_bad(*p4d)) {
    	return NULL;
    }

    pud_t *pud = pud_offset(p4d, address);
    if (pud_none(*pud) || pud_bad(*pud)) {
        return NULL;
    }

    pmd_t *pmd = pmd_offset(pud, address);
    if (pmd_none(*pmd) || pmd_bad(*pmd)) {
        return NULL;
    }

    pte_t *pte = pte_offset_kernel(pmd, address);
    if (!pte) {
        return NULL;
    }

    return pte_page(*pte);
	

}


static size_t write_page_struct(char __user *ubuf,
				struct task_struct *task_struct_ref,
				unsigned long address) {
	char buf[BUF_SIZE];
	size_t len = 0;

	struct page *page_struct;
	struct mm_struct *mm = task_struct_ref->mm;


	if (mm == NULL) {
		sprintf(buf, "Task_struct's mm is NULL\n");
		return 0;
	}
	struct vm_area_struct *vm_current = mm->mmap;
	unsigned long start = vm_current->vm_start;
	unsigned long end = vm_current->vm_end;
	while (start <= end) {
		page_struct = get_my_page(mm, start);
		if (page_struct != NULL) {
			len += sprintf(buf + len, "flags = %ld\n", page_struct->flags);
			len += sprintf(buf + len, "va = %x\n", start);
			len += sprintf(buf + len, "pa= %x\n", page_struct->mapping);
			break;
		}
		start += PAGE_SIZE;
	}
	if (copy_to_user(ubuf, buf, len)) {
		return -EFAULT;
	}
	return len;

}


static size_t write_vm_area_struct(char __user *ubuf,
				struct task_struct *task_struct_ref,
				unsigned long address) {
	char buf[BUF_SIZE];
	size_t len = 0;
	struct mm_struct *mm = task_struct_ref->mm;
	if (mm == NULL) {
		sprintf(buf, "Task_struct's mm is NULL\n");
		return 0;
	}
	struct vm_area_struct *vm_current = mm->mmap;
	len += sprintf(buf + len, "vm_start = %ld\n", vm_current->vm_start);
	len += sprintf(buf + len, "vm_end = %ld\n", vm_current->vm_end);
	len += sprintf(buf + len, "rm_subtree_gap = %ld\n", vm_current->rb_subtree_gap);
	len += sprintf(buf + len, "vm_flags = %ld\n", vm_current->vm_flags);
	len += sprintf(buf + len, "vm_pgoff = %ld\n", vm_current->vm_pgoff);
	len += sprintf(buf + len, "swap_readahead_info = %ld\n", (vm_current->vm_flags));
	if (copy_to_user(ubuf, buf, len)) {
		return -EFAULT;
	}
	return len;


}	


// otkritie
static int open_proc(struct inode *inode, struct file *file) 
{ 
    printk(KERN_INFO "proc file opend.....\t"); 
    return 0; 
} 
 
//zakritie
static int release_proc(struct inode *inode, struct file *file) 
{ 
    printk(KERN_INFO "proc file released.....\n"); 
    return 0; 
}

//chetie

static ssize_t read_proc(struct file *filp, 
			char __user *ubuf, 
			size_t count, 
			loff_t *ppos) { 
 
    char buf[BUF_SIZE]; 
    int len = 0; 
    struct task_struct *task_struct_ref = get_pid_task(find_get_pid(pid), PIDTYPE_PID); 
     
    printk(KERN_INFO "proc file read.....\n"); 
    if (*ppos > 0 || count < BUF_SIZE){ 
        return 0; 
    } 
 
    if (task_struct_ref == NULL){ 
        len += sprintf(buf,"task_struct for pid %d is NULL.\n",pid); 
 
        if (copy_to_user(ubuf, buf, len)){ 
            return -EFAULT; 
        } 
        *ppos = len; 
        return len; 
    } 
 
    switch(struct_id){ 
        default: 
        case 0: 
            len = write_page_struct(ubuf, task_struct_ref, adress); 
            break; 
        case 1: 
		len = write_vm_area_struct(ubuf, task_struct_ref, adress);
            break; 
    } 
 
    *ppos = len; 
    return len; 
}


// zapis'
static ssize_t write_proc(struct file *filp, const char __user *ubuf, size_t 
count, loff_t *ppos) { 
 
    int num_of_args, c, a, b, adr; 
    char buf[BUF_SIZE]; 
     
    printk(KERN_INFO "proc file wrote.....\n"); 
 
    if (*ppos > 0 || count > BUF_SIZE){ 
        return -EFAULT; 
    } 
 
    if( copy_from_user(buf, ubuf, count) ) { 
        return -EFAULT; 
    } 
 
    num_of_args = sscanf(buf, "%d %d", &a, &b); 
    if (num_of_args != 2){ 
        return -EFAULT; 
    } 
 
    struct_id = a; 
    pid = b;
 
    c = strlen(buf); 
    *ppos = c; 
    return c; 
}

// init
static int __init lab_driver_init(void) { 

    parent = proc_mkdir("lab2",NULL); 
 
    if( parent == NULL ) 
    { 
        pr_info("Error creating proc entry"); 
        return -1; 
    } 
 
    proc_create("struct_info", 0666, parent, &proc_fops); 
 
    pr_info("Device Driver Insert...Done!!!\n"); 
    return 0; 
} 
 
// exit from module
static void __exit lab_driver_exit(void) 
{ 
    // dlya 1 zapisi
    //remove_proc_entry("lab/struct_info", parent); 
 
    // dlya full udaleniya
    proc_remove(parent); 
     
    pr_info("Device Driver Remove\nDone.\n"); 
} 
 
module_init(lab_driver_init); 
module_exit(lab_driver_exit);




