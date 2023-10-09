# How to build

    ./gradlew build

# How to run

    $ java -jar build/libs/casino.jar ./src/test/resources/ru/vladimir/loshchin/casino/7sJh7c.png
    ./src/test/resources/ru/vladimir/loshchin/casino/7sJh7c.png - 7sJh7c

# Implementation

В основе механизма распознавания карт лежит идея создания матричного представления значения и масти
карты с последующей проверкой текущей карты XOR-ом и подсчётом различий.
