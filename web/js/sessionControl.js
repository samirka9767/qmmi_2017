/*
 *  Created by S. Vugar
 *
 */

(function ($) {
    var liveTime = 0;
    var methods = {
        init: function (options) {
            var __settings = $.extend({
                'timeout': 60
                , 'sessionDestroyIdentificator': '1'
                , 'sessionCheckerResponceHeader': 'requires_auth'
            }, options);
            setInterval(function () {
                $.sessionControl('incrementTime');
                if (liveTime >= __settings.timeout) {
                    window.location.reload();
                }
            }, 1000);
            $(document).ajaxSuccess(function (event, request, settings) {
                if (request.getResponseHeader(__settings.sessionCheckerResponceHeader) == __settings.sessionDestroyIdentificator) {
                    window.location.reload();
                } else {
                    $.sessionControl('destroy');
                }
            });
        },
        destroy: function () {
            liveTime = 0;
        },
        incrementTime: function () {
            liveTime++;
        },
        getTime: function () {
            return liveTime;
        }
    };

    $.sessionControl = function (method) {
        if (methods[method]) {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            console.log(method + ' not specified for jQuery.sessionControl');
        }
    };
})(jQuery);