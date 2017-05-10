
// TODO: code logic here

Vue.component('hero', function heroCreator(resolve, reject) {
    $.ajax('/assets/templates/hero-template.html').done((template)=>{
        resolve({
            template: template,
            props: ['bg-image', 'bg-color', 'title', 'link-url'],
            computed: {
                background: function () {
                    return this.bgColor + ' url(' + this.bgImage + ')';
                }
            },
            mounted: function () {

                var tl = new TimelineLite(),
                    title = $(".Hero-title"),
                    cta = $(".Hero-cta"),
                    background = $(".Hero");

                tl.from(background, 1.0, { opacity: 0 }, "0");
                tl.from(title, 1, { opacity: 0, top: 100 }, "-=0.8");
                tl.from(cta, 1, { opacity: 0, top: 110 }, "-=0.7");
                tl.play();
            }
        });
    });
    
});

Vue.component('xumak-text', {
    template: '<div><slot></slot></div>',
    mounted: function () {
        /*
        var tlXumakText = new TimelineMax();
        var tlXumakText2 = new TimelineMax();
        XumakText = $("#XumakText");
        tlXumakText.to(XumakText, 1, { left :'-10px'});
        tlXumakText.play();

        new ScrollMagic.Scene({
            duration: '30%'
        })
        .setTween(tlXumakText2)
        .triggerHook(0)
        .triggerElement('#ColumnsAnimation')
        .addIndicators({name: " ColumnsAnimation "})
        .addTo(this.controller);*/
    }
});


