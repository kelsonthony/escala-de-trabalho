$(document).ready(function() {
    var table = $('#example').DataTable( {
        scrollY:        true,
        scrollX:        true,
        scrollCollapse: true,
        paging:         true,
        fixedColumns:   {
            leftColumns: 3
        }
    } );
} );