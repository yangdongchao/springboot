var dropdown = $('.dropdown');
var item = $('.item');

item.on('click', function() {
  item.toggleClass('collapse');
  
  if (dropdown.hasClass('dropped')) {
    dropdown.toggleClass('dropped');
  } else {
    setTimeout(function() {
      dropdown.toggleClass('dropped');
    }, 150);
  }
})