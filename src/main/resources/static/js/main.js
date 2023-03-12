$(document).ready(function () {
   $('.eBtn').click(function (event) {

      event.preventDefault();

      var href = $(this).attr('href');

      $.get(href,function (user) {
         $('.editForm #id').val(user.id);
         $('.editForm #username').val(user.username);
         $('.editForm #lastName').val(user.lastName);
         $('.editForm #age').val(user.age);
         $('.editForm #password').val(user.password);
      })

      $('#editModal').modal();
   });

   $('.dBtn').click(function (event) {

      event.preventDefault();

      var href = $(this).attr('href');

      $.get(href,function (user) {
         $('.deleteForm #idDel').val(user.id);
         $('.deleteForm #usernameDel').val(user.username);
         $('.deleteForm #lastNameDel').val(user.lastName);
         $('.deleteForm #ageDel').val(user.age);
         $('.deleteForm #passwordDel').val(user.password);
         $('.deleteForm #delRef').attr('href', "/admin/deleteUser/?UserID=" + user.id)
      })

      $('#deleteModal').modal();
   });
});