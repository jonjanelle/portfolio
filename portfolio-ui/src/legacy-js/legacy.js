function collapseNavbar() {
    if ($(".navbar").offset().top > 50) {
        $(".fixed-top").addClass("top-nav-collapse");
    } else {
        $(".fixed-top").removeClass("top-nav-collapse");
    }
}

$(document).ready(function(){

  collapseNavbar();
  $(window).scroll(collapseNavbar);
  /*
   * Decrease header opacity on page scroll down.
   * Parallax effect on top image
   */
  $(window).scroll(function() {
    if (screen.width >= 768){
      var scrolledY = $(this).scrollTop();
      $('#first-img').css('background-position', 'left ' + scrolledY + 'px');
    }
    $(".bgimg-1 .dark-strip").css({
      'opacity': 1 - (($(this).scrollTop()) / 350)
    });
  });

  /*
   * Setup sliding panel effect for project boxes on mouse enter/leave
   */
  $(".project-box").mouseenter(function() {
      jQuery(this).children("div").slideDown();
      $(this).children( "img" ).css('transform', 'scale(1.5)');
  });

  $(".project-box").mouseleave(function() {
      jQuery(this).children("div").slideUp();
      $(this).children( "img" ).css('transform', 'scale(1)');
  });

  //close nav menu on click outside
  $("body").click(function(event) {
       if ($(".navbar-collapse").is(":visible") && $(".navbar-toggle").is(":visible") ) {
          $('.navbar-collapse').collapse('toggle');
      }
  });

  //Smooth page scroll transitions
  $('a.page-scroll').bind('click', function(event) {
      var $anchor = $(this);
      $('html, body').stop().animate({
          scrollTop: $($anchor.attr('href')).offset().top
      }, 1000, 'easeInOutExpo');
      event.preventDefault();
  });

});