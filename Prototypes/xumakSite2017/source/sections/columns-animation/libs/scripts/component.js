
// TODO: code logic here

 Vue.component( 'xmk-columns', {
        props: [
            'time',
            'controller'
        ],
        data: function() {
            return {

            }
        },
        methods:{
            focusIn: function(){
                this.focused = true;
            },
            focusOut:function(){
                this.focused = false;
            }
        },
        mounted:function(){

            var tl = new TimelineLite(),
                oneColumn = $( this.$el ).find( '.one-box' ),
                twoColumn = $( this.$el ).find( '.two-box' ),
                threeColumn = $( this.$el ).find( '.three-box' ),
                columnsAnimation = $( this.$el ).find( '.ColumnsAnimation-title' );

            if ( document.body.scrollTop+(window.innerHeight/2) < this.$el.offsetTop  ){

              tl.from( columnsAnimation, 1, { top:250 }, '0' );
              tl.from( oneColumn, 1, { top:250 }, '-=0.4' );
              tl.from( twoColumn, 1, { top:250 }, '-=0.7' );
              tl.from( threeColumn, 1, { top:250 }, '-=0.7' );
              tl.stop();
            }

            new ScrollMagic.Scene({
                duration: '20%'
            })
            .triggerHook(0.40)
            .triggerElement(this.$el)
            .addIndicators({name: " ColumnsAnimation"})
            .addTo(this.controller)
            .on("enter", function(){
                tl.play();
            })

        },
        template: '<div><slot></slot></div>',
    } );