window.addEventListener('DOMContentLoaded', () => {
    const sortButton = document.getElementById('sort-button');
    const sortMenu = document.getElementById('sort-menu');
    const sortMenuItems = document.querySelectorAll('.sort-menu-item');
    const sortDirectionButton = document.getElementById('sort-direction');

    let currentSortKey = sortButton.dataset.currentSort;
    let currentSortDirection = sortDirectionButton.dataset.currentDirection;

    // ソートメニューの表示/非表示
    sortButton.addEventListener('click', (e) => {
        e.preventDefault();
        const isVisible = sortMenu.style.display === 'block';
        sortMenu.style.display = isVisible ? 'none' : 'block';
    });

    // メニュー外クリックで閉じる
    document.addEventListener('click', (event) => {
        if (!event.target.closest('.sort-header')) {
            sortMenu.style.display = 'none';
        }
    });

    //検索フォームの値をセットする
    function setSearchFormValue() {
        const searchForm = document.getElementById('search-form');
        const sortKey = currentSortKey
        const sortDirection = currentSortDirection;

        document.getElementById('search-sort').value = sortKey;
        document.getElementById('search-order').value = sortDirection;

        //inputタグに入力がない場合、そのinputタグを送信しない
        const inputs = searchForm.querySelectorAll('input');
        inputs.forEach(input => {
            if (input.value === '') {
                input.disabled = true;
            }
        });

        //selectタグに選択が''の場合、そのselectタグを送信しない
        const selects = searchForm.querySelectorAll('select');
        selects.forEach(select => {
            if (select.value === '') {
                select.disabled = true;
            }
        });
    }

    // ソートキーの選択
    sortMenuItems.forEach(item => {
        item.addEventListener('click', (e) => {
            e.preventDefault();
            currentSortKey = item.dataset.sort;
            updateSortButtonLabel();
            sortMenu.style.display = 'none';
        });
    });

    // 昇順/降順の切り替え
    sortDirectionButton.addEventListener('click', (e) => {
        e.preventDefault();
        currentSortDirection = currentSortDirection === 'asc' ? 'desc' : 'asc';
        updateSortDirectionButton();
    });

    // ソートボタンのラベル更新
    function updateSortButtonLabel() {
        const selectedItem = document.querySelector(`.sort-menu-item[data-sort="${currentSortKey}"]`);
        const sortValueElement = document.getElementById('sort-value');
        const label = selectedItem.textContent.trim();
        sortValueElement.textContent = label;
    }

    // ソート方向ボタンの更新
    function updateSortDirectionButton() {
        if (currentSortDirection === 'asc') {
            sortDirectionButton.innerHTML = '<span class="direction-icon">↑</span> 昇順';
        } else {
            sortDirectionButton.innerHTML = '<span class="direction-icon">↓</span> 降順';
        }
    }

    //検索ボタンをクリックしたときに検索を実行する
    const searchButton = document.getElementById('search-button');
    searchButton.addEventListener('click', (event) => {
        setSearchFormValue();
        document.getElementById('search-form').submit();
    });

    //前のページボタン、次のページボタンをクリックしたときにページを移動する
    const prevPageButton = document.getElementById('prev-page');
    const nextPageButton = document.getElementById('next-page');
    prevPageButton.addEventListener('click', (event) => {
        setPrevPage();
        setSearchFormValue();

        const pagerForm = document.getElementById('pager-form');
        const searchSort = document.getElementById('search-sort');
        const searchOrder = document.getElementById('search-order');
        pagerForm.appendChild(searchSort);
        pagerForm.appendChild(searchOrder);

        pagerForm.submit();
    });

    nextPageButton.addEventListener('click', (event) => {
        setNextPage();
        setSearchFormValue();

        const pagerForm = document.getElementById('pager-form');
        const searchSort = document.getElementById('search-sort');
        const searchOrder = document.getElementById('search-order');
        pagerForm.appendChild(searchSort);
        pagerForm.appendChild(searchOrder);

        pagerForm.submit();
    });

    // 初期状態の設定
    updateSortButtonLabel();
    updateSortDirectionButton();
});

//前のページ番号の値をセットする
function setPrevPage() {
    const prevPage = document.getElementById("prev-page").dataset.prevPage;
    document.getElementById("page").value = prevPage;
}

//次のページ番号の値をセットする
function setNextPage() {
    const nextPage = document.getElementById("next-page").dataset.nextPage;
    document.getElementById("page").value = nextPage;
}