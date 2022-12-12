
import java.util.Map;
import java.util.Objects;

public class notebook {
    protected static int number = 0;
    private int id = 0;
    private String model;
    private String processor;
    private int memory;
    private int hardDisk;
    private String color;
    private String operationSystem;

    public notebook(String model, String processor, int memory, int hardDisk, String color, String operationSystem) {
        notebook.number++;
        this.id = notebook.number;
        this.model = model;
        this.processor = processor;
        this.memory = memory;
        this.hardDisk = hardDisk;
        this.color = color;
        this.operationSystem = operationSystem;
    }

    public notebook(String model, String processor, int memory, int hardDisk, String color) {
        this(model, processor, memory, hardDisk, color, null);
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "notebook{" +
                "id: " + id +
                ", Модель: '" + model + '\'' +
                ", Процуссор: '" + processor + '\'' +
                ", ОЗУ: " + memory +
                ", SSD: " + hardDisk +
                ", Цвет: '" + color + '\'' +
                ", ОС: '" + operationSystem + '\'' +
                '}';
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public void setHardDisk(int hardDisk) {
        this.hardDisk = hardDisk;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public int getId() {
        return id;
    }

    public String getProcessor() {
        return processor;
    }

    public int getMemory() {
        return memory;
    }

    public int getHardDisk() {
        return hardDisk;
    }

    public String getColor() {
        return color;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        notebook notebook = (notebook) o;
        return id == notebook.id && memory == notebook.memory && hardDisk == notebook.hardDisk
                && model.equals(notebook.model) && processor.equals(notebook.processor) && color.equals(notebook.color)
                && Objects.equals(operationSystem, notebook.operationSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, processor, memory, hardDisk, color, operationSystem);
    }

    public boolean like(Map<String, Object> filter) {
        return memory >= (int) filter.get("ОЗУ") &&
                hardDisk >= (int) filter.get("SSD") &&
                (color.equals((String) filter.get("Цвет")) || (filter.get("Цвет") == null) &&
                        (operationSystem.equals((String) filter.get("ОС")) || (filter.get("ОС") == null)));
    }
}