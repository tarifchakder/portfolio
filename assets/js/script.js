'use strict';



// element toggle function
const elementToggleFunc = function (elem) { elem.classList.toggle("active"); }



// sidebar variables
const sidebar = document.querySelector("[data-sidebar]");
const sidebarBtn = document.querySelector("[data-sidebar-btn]");

// sidebar toggle functionality for mobile
sidebarBtn.addEventListener("click", function () { elementToggleFunc(sidebar); });

// custom select variables
const select = document.querySelector("[data-select]");
const selectItems = document.querySelectorAll("[data-select-item]");
const selectValue = document.querySelector("[data-selecct-value]");
const filterBtn = document.querySelectorAll("[data-filter-btn]");

select.addEventListener("click", function () { elementToggleFunc(this); });

// add event in all select items
for (let i = 0; i < selectItems.length; i++) {
  selectItems[i].addEventListener("click", function () {

    let selectedValue = this.innerText.toLowerCase();
    selectValue.innerText = this.innerText;
    elementToggleFunc(select);
    filterFunc(selectedValue);

  });
}

// filter variables
const filterItems = document.querySelectorAll("[data-filter-item]");

const filterFunc = function (selectedValue) {

  for (let i = 0; i < filterItems.length; i++) {

    if (selectedValue === "all") {
      filterItems[i].classList.add("active");
    } else if (selectedValue === filterItems[i].dataset.category) {
      filterItems[i].classList.add("active");
    } else {
      filterItems[i].classList.remove("active");
    }

  }

}

// add event in all filter button items for large screen
let lastClickedBtn = filterBtn[0];

for (let i = 0; i < filterBtn.length; i++) {

  filterBtn[i].addEventListener("click", function () {

    let selectedValue = this.innerText.toLowerCase();
    selectValue.innerText = this.innerText;
    filterFunc(selectedValue);

    lastClickedBtn.classList.remove("active");
    this.classList.add("active");
    lastClickedBtn = this;

  });

}



// contact form variables
const form = document.querySelector("[data-form]");
const formInputs = document.querySelectorAll("[data-form-input]");
const formBtn = document.querySelector("[data-form-btn]");

// add event to all form input field
for (let i = 0; i < formInputs.length; i++) {
  formInputs[i].addEventListener("input", function () {

    // check form validation
    if (form.checkValidity()) {
      formBtn.removeAttribute("disabled");
    } else {
      formBtn.setAttribute("disabled", "");
    }

  });
}



// page navigation variables
const navigationLinks = document.querySelectorAll("[data-nav-link]");
const pages = document.querySelectorAll("[data-page]");

navigationLinks.forEach((link) => {
    link.addEventListener("click", function () {
        const targetPage = link.textContent.trim().toLowerCase();

        // Toggle pages
        pages.forEach((page) => {
            if (page.dataset.page.trim().toLowerCase() === targetPage) {
                page.classList.add("active");
            } else {
                page.classList.remove("active");
            }
        });

        // Toggle nav link active state
        navigationLinks.forEach((nav) => nav.classList.remove("active"));
        link.classList.add("active");

        window.scrollTo(0, 0);
    });
});

//
const titles = [
    "❤️ Android",
    "❤️ KMP Enthusiast",
    "✍️ Blogger",
    "🌍 Contributor"
];

let index = 0;
const titleEl = document.querySelector(".title");

function changeTitle() {
    if (!titleEl) return;

    // Start fade-out
    titleEl.classList.add("fade-out");

    // Wait for fade-out, then change text and fade back in
    setTimeout(() => {
        index = (index + 1) % titles.length;
        titleEl.textContent = titles[index];
        titleEl.classList.remove("fade-out");
    }, 800); // match this with CSS transition duration
}

// Initial call and set interval
setInterval(changeTitle, 5000);



// Pop up dialog
const comingButtons = document.querySelectorAll(".comingSoonBtn");
const popup = document.getElementById("comingSoonPopup");
const closePopup = document.getElementById("closePopup");

// Handle click for all coming soon buttons
comingButtons.forEach(button => {
    button.addEventListener("click", (e) => {
        e.preventDefault(); // ⛔ prevent <a> from navigating
        popup.style.display = "flex";
    });
});

// Close popup
closePopup.addEventListener("click", () => {
    popup.style.display = "none";
});

// Close popup if clicked outside content
window.addEventListener("click", (e) => {
    if (e.target === popup) {
        popup.style.display = "none";
    }
});