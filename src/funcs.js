function getPlayChoicr(pt)
{
    if (pt._marriage) {
        return "Брак по итальянски";
    } else if (pt._figaro) {
        return "Безумный день, или Женитьбя Фигаро";
    } else if (pt._violinist) {
        return "Скрипач на крыше";
    }
}