/*!
    * Start Bootstrap - SB Admin v6.0.1 (https://startbootstrap.com/templates/sb-admin)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    (function($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
            if (this.href === path) {
                $(this).addClass("active");
            }
        });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function(e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });

    // Toggle the input type and value to month field
    $("#btnSalvar").on("click", function(e){
        e.preventDefault();
        var data = document.getElementById("data").value;
        if (typeof data === 'undefined' || data === null || data === '') {
            data = null;
        } else {
            data += "-01";
        }
        document.getElementById("data").type = "text";
        document.getElementById("data").value = data;
        $( "#escala" ).submit();
    });

})(jQuery);