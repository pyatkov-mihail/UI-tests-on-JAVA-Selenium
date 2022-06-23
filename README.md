# UI-tests-on-JAVA-Selenium
Инструкция по разворачиванию UI-автотестов на JAVA + Selenium

Для запуска понадобятся:

1. Установленная среда разработки Intellij IDEA;
2. Установленные Java (jdk/openjdk) и Maven, прописанные в системные окружения ОС;
3. Браузер Chrome и chromedriver — программа для передачи команд браузеру.

Обратите внимание, полный путь к файлу chromedriver необходимо вписать в текст программы "Main.java".

Открыть Intellij IDEA.
Нажать File - New - Project from Version Control...
Во вкладке Repository URL выбрать:
1. Version control - Git;
2. URL - https://github.com/pyatkov-mihail/UI-tests-on-JAVA-Selenium
3. Directory - папка на вашем ПК, в которую будет клонирован проект.
4. Нажать Clone

Подтвердить что вы доверяете этому источнику.
Выбрать как открывать проект - в новом или текущем окне.

Открыть файл Main.java, ввести полный путь к файлу chromedriver, используя "\\" для обозначения папок.

Запустить выполнение любым удобным способом.
