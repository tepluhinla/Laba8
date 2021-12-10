# Laba8
Laba 8 Mobilki (TepluhinLev 803a2)

1) Создаём проект 
2) Добавим на форму 3 компонента «EditText*», 1 «ListView», а также 3 компонентов «Button» «Добавить», «Сохранить», «Открыть».

![image](https://user-images.githubusercontent.com/73265867/143860914-f32cc325-44cc-4dba-957d-80281658ff61.png)

3) Для работы с форматом json добавляем пакет com.google.code.gson в файл guild.gradle, в секцию dependencies

![image](https://user-images.githubusercontent.com/73265867/143862491-2585ed45-5c61-4376-af55-19a0d2a8126f.png)

4) Для работы с json добавляем класс JSONHelper (с метанита)

public class JSONHelper {

    private static final String FILE_NAME = "data.json";

    static boolean exportToJSON(Context context, List<Employees> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setPets(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    static List<Employees> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);
            return  dataItems.getEmployees();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItems {
        private List<Employees> employees;

        List<Employees> getEmployees() {
            return employees;
        }
        void setEmployees(List<Employees> employees) {
            this.employees = employees;
        }
    }
}


5) Добавялем Java класс Employees (сотрудники)

package com.example.laba8;

public class Employees {
    
    private String Name;
    private String workingposition;
    private int age;

    Employees(String name, String workingposition, int age){

        this.Name = name;
        
        this.workingposition = workingposition;
        
        this.age = age;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getWorkingposition() {
        return workingposition;
    }
    public void setWorkingposition(String workingposition) {
        this.workingposition = workingposition;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public  String toString()
    {
        return "Имя сотрудника: " + Name + "\n Должность: " + workingposition + "\n Возраст: " + age;
    }
}

6) В MainActivity пишем код и добавим обработчики кнопок

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Employees> adapter;
    private EditText nameText, workingpositionText, ageText;
    private List<Employees> employees;
    ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        workingpositionText = findViewById(R.id.workingpositionText);
        ageText = findViewById(R.id.ageText);
        listView = findViewById(R.id.list);
        employees = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        listView.setAdapter(adapter);
    }

    public void addWorkingposition(View view){
        String name = nameText.getText().toString();
        String workingposition = workingpositionText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        Employees employee = new Employees(name, workingposition, age);
        employees.add(employee);
        adapter.notifyDataSetChanged();
    }

    public void save(View view){

        boolean result = JSONHelper.exportToJSON(this, employees);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Данные не сохранены", Toast.LENGTH_LONG).show();
        }
    }
    public void open(View view){
        employees = JSONHelper.importFromJSON(this);
        if(employees!=null){
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            listView.setAdapter(adapter);
            Toast.makeText(this, "Данные восстановлены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Данные не открыты", Toast.LENGTH_LONG).show();
        }
    }
}

7) Проверяем работоспособность приложения:

Добавляем сотрудника Lev 

![image](https://user-images.githubusercontent.com/73265867/143866385-5fdc6cb2-7996-4258-b88d-fa873451ed20.png)

Сохраняем сотрудника Lev

![image](https://user-images.githubusercontent.com/73265867/143866409-9d35d0b8-8e50-4797-834b-298d60f205be.png)

Добавляем сотрудника Danul и сразу его сохраняем

![image](https://user-images.githubusercontent.com/73265867/143866667-f03e372d-279d-4d69-8e3f-fc95bf4ca81d.png)

Добавляем сотрудника Andrew но не сохраняем его

![image](https://user-images.githubusercontent.com/73265867/143866809-b43a7829-1ffd-4a0a-8a46-ec9ebf3817c2.png)

Нажимаем кнопку "Открыть" и видим сохраненых сотрудников Lev и Danil, программа работает))

![image](https://user-images.githubusercontent.com/73265867/143866823-aa36ce8d-fffe-4de0-9f74-2e70e2893855.png)

   
   
   
