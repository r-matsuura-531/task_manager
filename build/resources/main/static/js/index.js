window.addEventListener('DOMContentLoaded', () => {
    const deleteModal = document.getElementById('delete-modal');
    const deleteButton = document.querySelectorAll('.js-delete-button');
    const deleteModalChancelButton = document.getElementById('delete-modal-chancel');

    function hideDeleteModal() {
        deleteModal.style.display = 'none';
    }

    deleteButton.forEach((button) => {
        button.addEventListener('click', () => {
            const taskId = button.dataset.taskId;
            const taskTitle = button.dataset.taskTitle;
            const taskDescription = button.dataset.taskDetails;
            const taskDueDate = button.dataset.taskDueDate;

            document.getElementById('delete-task-title').textContent = taskTitle;
            document.getElementById('delete-task-details').textContent = taskDescription;
            document.getElementById('delete-task-due-date').textContent = taskDueDate;

            const confirmDeleteButton = document.getElementById('confirm-delete');
            confirmDeleteButton.addEventListener('click', () => {
                const form = document.createElement('form');
                const deleteModal = document.getElementById('delete-modal');
                form.id = 'delete-form';
                form.action = '/delete/' + taskId;
                form.method = 'post';
                form.style.display = 'none';
                deleteModal.appendChild(form);
                form.submit();
            })

            deleteModal.style.display = 'block';

        })
    })

    document.addEventListener('click', (event) => {
        if (event.target === deleteModal) {
            hideDeleteModal();
        }
    })

    deleteModalChancelButton.addEventListener('click', () => {
        hideDeleteModal()
    })
})
