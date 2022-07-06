// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable( {
    language: {
      lengthMenu: "Exibir _MENU_ registros por página.",
      search: "Buscar:",
      zeroRecords: "Nada encontrado - Desculpe!",
      info: "Mostrando página _PAGE_ de _PAGES_.",
      infoEmpty: "Não há registros disponíveis.",
      infoFiltered: "(filtrado de _MAX_ registros totais).",
      paginate: {
              first:      "Primeira",
              last:       "Última",
              next:       "Próximo",
              previous:   "Anterior"
          },
    },
    lengthMenu: [5, 10, 25, 50, 100]
  } );
});
