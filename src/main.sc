require: patterns.sc
   
theme: /

    state: Start
        q!: *start
        q!: * $hello *
        q: * (отмен*|стоп|хватит) * || fromState = /SuggestPlay
        random:
            a: Добрый день! Я помогу вам купить билет в театр Качалова.
            a: Здравствуйте! Могу помочь с приобретением билета в театр Качалова.
        go!: /SuggestPlay
            
    state: CatchAll || noContext = true
        event!: noMatch
        random:
            a: Простите, я вас не понял!
            a: Извините, я вас не понимаю.
        random:
            a: Попробуйте ответить по-другому.
            a: Переформулируйте, пожалуйста, ваш вопрос.
            
    state: SuggestPlay || modal = true
        a: Какой спектакль вас интересует?
        a: {{$request.channelType}}
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
            q: * (~брак|~итал) *
            q: * (женит*|~фигаро) *
            q: * (~скрипач|~крыша) *
            go!: /HowManyTickets
            
        state: LocalCatchAll
            event: noMatch
            a: Такаго спектакля пока нет на нашем реперутуаре. Выберите спектакль из списка.
            go!: ..
            
    state: HowManyTickets
        a: Скольно билетов вам нужно?
        

    