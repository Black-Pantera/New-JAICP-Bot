require: patterns.sc

init:
    bind("postProcess", function($context) {
        $context.session.lastState = $context.currentState;
    });
   
theme: /

    state: Start
        q!: *start
        q!: * $hello *
        q: * (отмен*|стоп|хватит) * || fromState = /SuggestPlay
        script:
            $temp.botName = capitalize($injector.botName);
        random:
            a: Добрый день! Меня зовут {{$temp.botName}}. Я помогу вам купить билет в театр Качалова.
            a: Здравствуйте! Меня зовут {{$temp.botName}}. Могу помочь с приобретением билетов в театр Качалова.
        script: $response.replies = $response.replies || [];
            $response.replies.push({
                type: "image",
                imageUrl: "https://teatrkachalov.ru/upload/resize_cache/iblock/864/1200_1000_1/l769kon782cznlnnm9ao0fgkdt48ut3h.jpg",
                text: "Tickets"
                });
        go!: /SuggestPlay
            
    state: CatchAll || noContext = true
        event!: noMatch
        random:
            a: Простите, я вас не понял!
            a: Извините, я вас не понимаю.
        random:
            a: Попробуйте ответить по-другому.
            a: Переформулируйте, пожалуйста, ваш вопрос.
        go!: {{$session.lastState}}
            
    state: SuggestPlay || modal = true
        a: Какой спектакль вас интересует?
        if: $request.channelType === "telegram"
            inlineButtons:
                { text: "Брак по итальянски", url: "https://teatrkachalov.ru/affiche/detail/?id=1246" }
                { text: "Безумный день, или Женитьбя Фигаро", url: "https://www.teatrkachalov.ru/affiche/detail/?id=1114" }
                { text: "Скрипач на крыше", url: "https://teatrkachalov.ru/affiche/detail/?id=373" }
        else:
            buttons:
                "Брак по итальянски"
                "Безумный день, или Женитьбя Фигаро" 
                "Скрипач на крыше" 
        
        state: ChoosePlay
            q: * ($marriage|$figaro|$violinist) *
            script:
                log("///////// MY LOG "+toPrettyString($parseTree))
            go!: /HowManyTickets
            
        state: LocalCatchAll
            event: noMatch
            a: Такаго спектакля пока нет в нашем реперутуаре. Выберите спектакль из списка.
            go!: ..
            
    state: HowManyTickets
        a: Скольно билетов вам нужно?
        

    