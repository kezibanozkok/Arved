$(document).ready( function () {
    var table = $('#allArticles').DataTable({

        "sAjaxSource": "/admin/allArticles",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "makaleAdi" },
            { "mData": "yazarListesi" },
            { "mData": "yil" }
        ]
    })

});