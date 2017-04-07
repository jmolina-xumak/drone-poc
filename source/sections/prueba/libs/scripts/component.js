// TODO: code logic here
(function () {
    new Vue({
            el: "#app",
            data: {
                name: "",
                gender: 'm',
                users: [
                    { name: 'Dani' },
                    { name: 'Carlos' },
                    { name: 'Juanfe' },
                ],
                message: 'Holi soy un mensaje personalizable',
                seen: true, //seen:false,
                color: 'blue',
            },
            methods: {
                changeColor: function (color) {
                   this.color = color;
                }
            }
        });
})();
