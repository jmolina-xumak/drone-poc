
// TODO: code logic here

  Vue.component('hero', {
            template: '#hero',
            props: ['bg-image', 'bg-color', 'title', 'link-url'],
            computed: {
                background: function () {
                    return this.bgColor + ' url(' + this.bgImage + ')';
                }
            },
            mounted: function () {
                    TweenMax.fromTo(
                        '.Hero', 1, {
                            css: {
                                opacity: 0
                            }
                        }, {
                            css: {
                                opacity: 1
                            }
                        }
                    )
                 }
                });

		


                               
        Vue.component('xumak-text', {
            template: '<div><slot></slot></div>',
            mounted: function() {

                var tlXumakText = new TimelineMax(),
                XumakText = $("#XumakText");
                tlXumakText.to("#XumakText", 1, { left :'-50vw'});
                tlXumakText.play();

                new ScrollMagic.Scene({
                    duration: '30%'
                })
                .setTween(tlXumakText)
                .triggerHook(0)
                .triggerElement('#ColumnsAnimation')
                .addIndicators({name: " ColumnsAnimation "})
                .addTo(this.controller);
            }

        }); 
              

     