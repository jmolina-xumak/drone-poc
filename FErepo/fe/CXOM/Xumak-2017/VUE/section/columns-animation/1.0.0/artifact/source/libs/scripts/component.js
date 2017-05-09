
// TODO: code logic here

 Vue.component( 'xmk-columns', {
        props: [
            'time',
            'controller'
        ],
        data: function () {
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
                oneColumn = $(".one-box"),
                twoColumn = $(".two-box"),
                threeColumn = $(".three-box"),
                ColumnsAnimation = $(".ColumnsAnimation-title");
                
                tl.from(ColumnsAnimation, 1, { top :250},"0");
                tl.from(oneColumn, 1, { top :250},"-=0.4");
                tl.from(twoColumn, 1, {top:250},"-=0.7");
                tl.from(threeColumn, 1, {top :250},"-=0.7"); 
                tl.play();
      

                new ScrollMagic.Scene({
                    duration: '20%'
                })
                .setTween(tl)
                .triggerHook(0.55)
                .triggerElement('#ColumnsAnimation')
                .addIndicators({name: " ColumnsAnimation "})
                .addTo(this.controller);
                
        },
        template: '<div><slot></slot></div>'
    } );