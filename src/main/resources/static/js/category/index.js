window.addEventListener('DOMContentLoaded', () => {
    const deleteModal = document.getElementById('delete-modal');
    const deleteButton = document.querySelectorAll('.js-delete-button');
    const deleteModalChancelButton = document.getElementById('delete-modal-chancel');

    function hideDeleteModal() {
        deleteModal.style.display = 'none';
    }

    deleteButton.forEach((button) => {
        button.addEventListener('click', () => {
            const categoryId = button.dataset.categoryId;
            const categoryName = button.dataset.categoryName;

            document.getElementById('delete-category-name').textContent = categoryName;


            const confirmDeleteButton = document.getElementById('confirm-delete');
            confirmDeleteButton.addEventListener('click', () => {
                const form = document.createElement('form');
                const deleteModal = document.getElementById('delete-modal');
                form.id = 'delete-form';
                form.action = 'category/delete/' + categoryId;
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
