/*Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. 
Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий (или критерии) 
фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. 
Например:
	“Введите цифру, соответствующую необходимому критерию:
	1 - ОЗУ
	2 - Объем ЖД
	3 - Операционная система
	4 - Цвет …
Далее нужно запросить минимальные значения для указанных критериев - 
сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class dz6_result {

    public static void main(String[] args) {
        List<notebook> notebookList = new LinkedList<>();
        notebookList.add(new notebook("iRU", "pentium", 4, 256, "Серебро", "dos"));
        notebookList.add(new notebook("baykal", "celeron", 4, 128, "Белый", "dos"));
        notebookList.add(new notebook("ibm", "i5", 32, 1024, "Черный", "macos"));
        notebookList.add(new notebook("iRU", "pentium", 4, 256, "Серебро"));
        notebookList.add(new notebook("baykal", "celeron", 4, 128, "Белый"));
        notebookList.add(new notebook("hp", "i5", 8, 256, "Белый", "windows"));
        notebookList.add(new notebook("lenovo", "i3", 4, 512, "Черный", "linux"));
        notebookList.add(new notebook("asus", "i9", 16, 512, "Серый", "windows"));

        for (notebook item : notebookList) {
            System.out.println(item);
        }

        Map<Integer, String> criteri = new TreeMap<>(Map.of(1, "ОЗУ",
                2, "SSD",
                3, "OC",
                4, "Цвет"));

        Map<String, Object> filter = new HashMap<>();
        filter.put("ОЗУ", (int) 0);
        filter.put("SSD", (int) 0);
        filter.put("Цвет", null);
        filter.put("ОС", null);

        filter = inputFilter(criteri);

        List<notebook> result = selectNotebook(filter, (LinkedList<notebook>) notebookList);

        for (notebook item : result) {
            System.out.println(item);
        }
    }

    public static Map<String, Object> inputFilter(Map<Integer, String> criteri) {
        int selectValue = 0;
        Map<String, Object> filter = new HashMap<>();
        filter.put("ОЗУ", (int) 0);
        filter.put("SSD", (int) 0);
        filter.put("Цвет", null);
        filter.put("ОС", null);

        Scanner scn = new Scanner(System.in, "Cp866");
        for (;;) {
            System.out.println("Введите цифру отбора или 0 для выхода и вывода результата:");
            for (Map.Entry<Integer, String> item : criteri.entrySet()) {
                System.out.println(item.getKey() + " - " + item.getValue());
            }
            try {
                selectValue = Integer.parseInt(scn.nextLine());
                if ((selectValue < 0) || (selectValue > criteri.size())) {
                    System.out.println("Выбор не сделан");
                    continue;
                }
                if (selectValue == 0) {
                    return filter;
                }
            } catch (Exception e) {
                System.out.println("Введено не верное значение");
                break;
            }
            System.out.println("Введите минимальное значение для поиска:");
            try {
                String valueInput = scn.nextLine();
                if (selectValue == 1) {
                    int findValue = Integer.parseInt(valueInput);
                    filter.put("ОЗУ", findValue);
                }
                if (selectValue == 2) {
                    int findValue = Integer.parseInt(valueInput);
                    filter.put("SSD", findValue);
                }
                if (selectValue == 3) {
                    filter.put("ОС", valueInput);
                }
                if (selectValue == 4) {
                    filter.put("Цвет", valueInput);
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так " + e.getLocalizedMessage());
                break;
            }
        }
        return filter;
    }

    public static List<notebook> selectNotebook(Map<String, Object> filter, LinkedList<notebook> notebookList) {
        List<notebook> resultSelect = new LinkedList<>();
        for (notebook item : notebookList) {
            if (item.like(filter)) {
                resultSelect.add(item);
            }
        }
        return resultSelect;
    }
}