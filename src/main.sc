require: patterns.sc
    module = patterns
    
theme: /

    state: Start
        q!: *start
        q!: * $hello *
        random:
            a: Добрый день! Я помогу вам купить билет в театр Качалова.
            a: Здравствуйте! Могу помочь с приобретением билета в театр Качалова.
            
    state: CatchAll
        event!: noMatch
        random:
            a: Простите, я вас не понял!
            a: Извините, я вас не понимаю.
        random:
            a: Попробуйте ответить по-другому.
            a: Переформулируйте, пожалуйста, ваш вопрос.

    