window.onload = function() {
    const dateCells = document.querySelectorAll('.dateRow');
    dateCells.forEach((dateCell) => {
        const originalDate = dateCell.textContent;
        const formattedDate = originalDate.replace('T', ' ');
        dateCell.textContent = formattedDate;
    });
};
