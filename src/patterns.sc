patterns:
    $hello = (салют|привет|здравствуй*|здарова|~добр * (~день|~вечер|~утро|~ночь))
    
    $marriage = (~брак|~итал)
    $figaro = (женит*|~Фигаро|~фигаро)
    $violinist = (~скрипач*|~крыш)
    
    $ticketRefund = ([салют|привет|здравствуй*|здарова] * (~сдать билет|~возврат * билета))
