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
        random:
            a: На какой спектакль вы хотите пойти?
            a: Какой спектакль вас интересует?
            buttons:
                "Брак по итальянски"
                "Женитьбя Фигаро" 
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
        

    