window.onload = function() {
    const dateCells = document.querySelectorAll('.dateRow');
    dateCells.forEach((dateCell) => {
        const originalDate = dateCell.textContent;
        const formattedDate = originalDate.replace('T', ' ');
        dateCell.textContent = formattedDate;
    });

    var navLinks = document.querySelectorAll(".pagination a");
    for (var i = 0; i < navLinks.length; i++) {
        if (navLinks[i].href == document.URL) {
            navLinks[i].className = "active";
        }
    }
};
