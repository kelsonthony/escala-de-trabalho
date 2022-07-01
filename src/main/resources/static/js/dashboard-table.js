$(document).ready(function() {
    var table = $('#dashboardTable').DataTable( {
        scrollY:        true,
        scrollX:        true,
        scrollCollapse: true,
        paging:         true,
        fixedColumns:   {
            leftColumns: 2
        },
        lengthMenu: [5, 10, 25, 50, 100]
    } );
} );