
// TODO: code logic here
Vue.component( 'xumak-text', {
    props: [
     'controller',
  ],
    template: '<p id="XumakText" class="XumakText"><slot></slot></p>',
    mounted: function() {

      var tlXumakText = new TimelineMax ();
      var tl2 = new TimelineLite(),
      contentParsys = document.getElementById( 'contentParsys' ),
      xumakText = this.$el,
      HeightChilds = contentParsys.lastElementChild.clientHeight + this.$el.parentElement.offsetHeight,
      distancia = xumakText.offsetWidth * -1;

      if ( document.body.scrollTop === 0 ){
        tlXumakText.from( this.$el, 1.5, { left: '100vw' }, '0' );
        tlXumakText.play( );
      }

      tl2.to( $( this.$el ), 1, { left: distancia + 'px' }, '0' );
      tl2.play();

      new ScrollMagic.Scene({
          duration: HeightChilds + 'px', //duration:'200%'
        } )
      .setTween( tl2 )
      .triggerHook( 0 )
      .triggerElement( '#xmkApp' )
      .addIndicators( {name: 'text '} )
      .addTo( this.controller );

    },
  });


Vue.component( 'hero', {
  template: '#hero',
  props: [ 'bg-image', 'bg-color', 'title', 'link-url' ],
  computed: {
    background: function() {
      return this.bgColor + ' url(' + this.bgImage + ')';
    },
  },
  mounted: function() {

    var tl = new TimelineLite( ),
    title = $( this.$el ).find( '.Hero-title' ),
    cta = $( this.$el ).find( '.Hero-cta' ),
    background = $( this.$el ).find( '.Hero' );

    tl.from( background, 1.0, { opacity: 0 }, '0' );
    tl.from( title, 1, { opacity: 0, top: 100 }, '-=0.8');
    tl.from( cta, 1, { opacity: 0, top: 110 }, '-=0.7' );
    tl.play();
  },
});





