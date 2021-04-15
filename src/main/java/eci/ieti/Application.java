package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;
import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        todoRepository.deleteAll();
        userRepository.deleteAll();

        todoRepository.save(new Todo(0L,"travel to Galapagos",10,"expired","charles" + 3 + "@natural.com","pending"));
        todoRepository.save(new Todo(1L,"travel to Galapagos aaaaa sssssssssss yyyyyyyyyy rrrrrrrrrr cva",10,"expired","charles" + 3 + "@natural.com","pending"));

        for(int i=2; i<25;i++){
            todoRepository.save(new Todo(i,"travel to Galapagos",10, i%2 == 0 ? "expired" : "Jan 10 - 186" + i,"charles" + i + "@natural.com","pending"));
        }  
        for(int i=0;i<10;i++){
            userRepository.save(new User(Integer.toString(i),"charles" + i,"charles" + i + "@natural.com"));
        }        

       

        //Todos where the dueDate has expired
        Query querye = new Query();
        System.out.println("Todos where the dueDate has expired");
        System.out.println("------------------------------------------------------");

        querye.addCriteria(Criteria.where("dueDate").is("expired"));
     
        mongoOperation.find(querye, Todo.class).stream().forEach(System.out::println);

        System.out.println();

        //Todos that are assigned to given user and have priority greater equal to 5
        Query queryp = new Query();

        System.out.println("Todos that are assigned to given user and have priority greater equal to 5");
        System.out.println("------------------------------------------------------");

        queryp.addCriteria(Criteria.where("responsible").is("charles2@natural.com").and("priority").gt(5));
     
        mongoOperation.find(queryp, Todo.class).stream().forEach(System.out::println);  
        System.out.println();


        //Users that have assigned more than 2 Todos.

        System.out.println("Users that have assigned more than 2 Todos.");
        System.out.println("------------------------------------------------------");


        userRepository.findAll().stream().forEach(user -> {
            Query queryt = new Query();
            queryt.addCriteria(Criteria.where("responsible").is(user.getEmail()));
            if (mongoOperation.find(queryt, Todo.class).size() > 2)
                System.out.println(user);
        });
        System.out.println();

        //Todos that contains a description with a length greater than 30 characters

        System.out.println("Todos that contains a description with a length greater than 30 characters");
        System.out.println("------------------------------------------------------");

        Query queryd = new Query();
        queryd.addCriteria(Criteria.where("description").regex(".{30,}"));
        mongoOperation.find(queryd, Todo.class).stream().forEach(System.out::println);

        System.out.println();

        //System.out.println(todo);
        /*
        System.out.println("-------------------------------");

        todoRepository.findByResponsibleContaining("@natural.com", PageRequest.of(0, 3)).stream()
        .forEach(System.out::println);

        System.out.println("-------------------------------");

        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();*/
    }

}