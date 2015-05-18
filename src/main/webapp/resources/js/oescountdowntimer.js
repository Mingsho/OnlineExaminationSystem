/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var Example2 = new (function() {
    var $countdown,
        $form, // Form used to change the countdown time
        incrementTime = 70,
        currentTime = 30000,
        updateTimer = function() {
            $countdown.html(formatTime(currentTime));
            if (currentTime == 0) {
                Example2.Timer.stop();
                timerComplete();
                Example2.resetCountdown();
                return;
            }
            currentTime -= incrementTime / 10;
            if (currentTime < 0) currentTime = 0;
        },
        timerComplete = function() {
            alert('Example 2: Countdown timer complete!');
        },
        init = function() {
            $countdown = $('#countdown');
            Example2.Timer = $.timer(updateTimer, incrementTime, true);
            $form = $('#example2form');
            $form.bind('submit', function() {
                Example2.resetCountdown();
                return false;
            });
        };
    this.resetCountdown = function() {
        var newTime = parseInt($form.find('input[type=text]').val()) * 100;
        if (newTime > 0) {currentTime = newTime;}
        this.Timer.stop().once();
    };
    $(init);
});