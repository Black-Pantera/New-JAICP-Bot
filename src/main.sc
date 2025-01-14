require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        q!: *(привет*|здравствуй*|здрасте|hi|хэллоу|здорова)
        q!: {~добр*| (~утро|день|вечер|ночь|~время суток)}*
        a: Начнём.

    