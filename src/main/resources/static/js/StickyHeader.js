// When the user scrolls the page, execute myFunction
window.onscroll = function() {myFunction()};

// Get the header
var header = document.getElementById("myHeader");

// Get the offset position of the navbar
var sticky = header.offsetTop;

// Add the sticky class to the header when you reach its scroll position. Remove "sticky" when you leave the scroll position
function myFunction() {
    if (window.pageYOffset > sticky) {
        header.classList.add("sticky");
    } else {
        header.classList.remove("sticky");
    }
}



///* Style the header */
//.header {
//    padding: 10px 16px;
//    background: #555;
//    color: #f1f1f1;
//}
//
///* Page content */
//.content {
//    padding: 16px;
//}
//
///* The sticky class is added to the header with JS when it reaches its scroll position */
//.sticky {
//    position: fixed;
//    top: 0;
//    width: 100%
//}
//
///* Add some top padding to the page content to prevent sudden quick movement (as the header gets a new position at the top of the page (position:fixed and top:0) */
//.sticky + .content {
//    padding-top: 102px;
//}