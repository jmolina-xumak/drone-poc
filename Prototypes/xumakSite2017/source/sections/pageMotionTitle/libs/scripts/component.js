
// TODO: code logic here
Vue.component( 'page-motion-title', {
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
  }
);
