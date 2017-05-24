
// TODO: code logic here
Vue.component( 'page-motion-title', {
    props: [
    'controller',
    ],
    template: '<div><slot></slot></div>',
    mounted: function() {

      var tlXumakText = new TimelineMax (),
      tl2 = new TimelineLite(),
      mainText = $(this.$el).find( '.pagaMotionTitle-component' ),
      distancia = mainText.offsetWidth * -1;
      console.log( distancia );
      if ( document.body.scrollTop === 0 ) {
        tlXumakText.from( mainText, 1.5, { left: '100vw' }, '0' );
        tlXumakText.play( );
      }

      tl2.to( mainText, 1, { left: distancia + 'px' }, '0' );
      tl2.play();

      new ScrollMagic.Scene({
        duration: '200%',
      } )
      .setTween( tl2 )
      .triggerHook( 0 )
      .triggerElement( '#xmkApp' )
      .addIndicators( {name: 'text '} )
      .addTo( this.controller );

    },
  }
);
