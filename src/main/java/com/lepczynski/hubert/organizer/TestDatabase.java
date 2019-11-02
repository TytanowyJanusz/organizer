package com.lepczynski.hubert.organizer;

/*
@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TaskRepository taskRepository, UserRepository userRepository) {
        return args -> {
            User user0 = new User("Bernan", "root", "kneorlan@gmail.com");

            Task task0 = new Task("Przygotować się do pracy",6, 7, Category.values()[0]);
            Task task1 = new Task("Ogarnąć RedMine i Eclipse", 4, 7, Category.values()[0], task0);


            user0.AddTask(task0);
            user0.AddTask(task1);
            log.info("Preloading " + userRepository.save(user0));

            log.info("Preloading " + taskRepository.save(task0));
            log.info("Preloading " + taskRepository.save(task1));

        };
    }
}
*/
