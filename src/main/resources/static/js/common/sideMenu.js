function sideMenuCurrentPage() {
  const sideBarMenu = document.querySelector('.sidebar-menu');
  if (!sideBarMenu) {
    return;
  }

  const sideMenu = sideBarMenu.querySelectorAll('li');
  const currentPage = window.location.pathname;

  sideMenu.forEach((menu) => {
    if (menu.dataset.menuUrl === currentPage) {
      menu.classList.add('active');
    }
  })
}

window.addEventListener('DOMContentLoaded', () => {
  sideMenuCurrentPage();
});