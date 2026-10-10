package com.collegebus.bus_tracker.config;

import com.collegebus.bus_tracker.model.Bus;
import com.collegebus.bus_tracker.model.Route;
import com.collegebus.bus_tracker.model.Student;
import com.collegebus.bus_tracker.repository.BusRepository;
import com.collegebus.bus_tracker.repository.RouteRepository;
import com.collegebus.bus_tracker.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(BusRepository busRepository, 
                                          RouteRepository routeRepository,
                                          StudentRepository studentRepository) {
        return args -> {
            // Refresh data if buses count is not 20 to ensure 20 buses across Hyderabad to Anurag University
            if (busRepository.count() < 20) {
                studentRepository.deleteAll();
                busRepository.deleteAll();
                routeRepository.deleteAll();

                // 20 Hyderabad Routes to Anurag University (Venkatapur, Ghatkesar)
                List<Route> routes = List.of(
                    new Route("R-101", "ECIL to Anurag University", "ECIL X Roads", "Anurag University", "07:15 AM", "04:30 PM", "ECIL, Radhika, Kushaiguda, Cherlapally, Ghatkesar, Campus Gate 1", "22 km", "45 mins", 1),
                    new Route("R-102", "Secunderabad to Anurag University", "Secunderabad Station", "Anurag University", "07:00 AM", "04:30 PM", "Secunderabad Stn, Tarnaka, Habsiguda, Uppal Depot, Ghatkesar, Campus Gate 1", "28 km", "55 mins", 1),
                    new Route("R-103", "Dilsukhnagar to Anurag University", "Dilsukhnagar Bus Depot", "Anurag University", "07:05 AM", "04:30 PM", "Dilsukhnagar, LB Nagar, Nagole, Uppal, Ghatkesar, Campus", "26 km", "50 mins", 1),
                    new Route("R-104", "Keesara to Anurag University", "Keesara Gutta", "Anurag University", "07:30 AM", "04:30 PM", "Keesara, Bogaram, Rampally, Ghatkesar Town, Campus", "18 km", "35 mins", 1),
                    new Route("R-105", "Uppal Depot to Anurag University", "Uppal Ring Road", "Anurag University", "07:35 AM", "04:30 PM", "Uppal Ring Road, Medipally, Chengicherla, Narapally, Korremula, Campus", "14 km", "30 mins", 1),
                    new Route("R-106", "Mehdipatnam to Anurag University", "Mehdipatnam Rythu Bazar", "Anurag University", "06:45 AM", "04:30 PM", "Mehdipatnam, Lakdikapul, Koti, Malakpet, Amberpet, Uppal, Campus", "34 km", "65 mins", 1),
                    new Route("R-107", "Kukatpally to Anurag University", "KPHB Colony", "Anurag University", "06:40 AM", "04:30 PM", "KPHB, Balanagar, Bowenpally, Secunderabad, Tarnaka, Campus", "38 km", "70 mins", 1),
                    new Route("R-108", "Miyapur to Anurag University", "Miyapur X Roads", "Anurag University", "06:35 AM", "04:30 PM", "Miyapur, Chandanagar, Ameerpet, Begumpet, Tarnaka, Campus", "42 km", "75 mins", 1),
                    new Route("R-109", "LB Nagar to Anurag University", "LB Nagar Ring Road", "Anurag University", "07:15 AM", "04:30 PM", "LB Nagar, Vanasthalipuram, Hayathnagar, Pedda Amberpet, ORR, Campus", "24 km", "40 mins", 1),
                    new Route("R-110", "Malkajgiri to Anurag University", "Malkajgiri X Roads", "Anurag University", "07:10 AM", "04:30 PM", "Malkajgiri, Anandbagh, Neredmet, ECIL, Cherlapally, Campus", "25 km", "50 mins", 1),
                    new Route("R-111", "Charminar to Anurag University", "Charminar Bus Stop", "Anurag University", "06:50 AM", "04:30 PM", "Charminar, Chandrayangutta, Santoshnagar, LB Nagar, Uppal, Campus", "32 km", "60 mins", 1),
                    new Route("R-112", "Banjara Hills to Anurag University", "Banjara Hills Road No 1", "Anurag University", "06:55 AM", "04:30 PM", "Banjara Hills, Panjagutta, RTC X Roads, Uppal, Ghatkesar, Campus", "33 km", "65 mins", 1),
                    new Route("R-113", "Lingampally to Anurag University", "Lingampally Station", "Anurag University", "06:30 AM", "04:30 PM", "Lingampally, Gachibowli, ORR Expressway, Ghatkesar, Campus", "45 km", "60 mins", 1),
                    new Route("R-114", "Alwal to Anurag University", "Alwal IG Statue", "Anurag University", "07:00 AM", "04:30 PM", "Alwal, Lothkunta, Tirumalagiri, Yapral, Cherlapally, Campus", "27 km", "52 mins", 1),
                    new Route("R-115", "Medchal to Anurag University", "Medchal Checkpost", "Anurag University", "06:35 AM", "04:30 PM", "Medchal, Kompally, Suchitra, Bowenpally, Tarnaka, Campus", "40 km", "70 mins", 1),
                    new Route("R-116", "Nagole to Anurag University", "Nagole Metro Station", "Anurag University", "07:25 AM", "04:30 PM", "Nagole, Bandlaguda, Boduppal, Peerzadiguda, Narapally, Campus", "16 km", "32 mins", 1),
                    new Route("R-117", "Habsiguda to Anurag University", "Habsiguda Circle", "Anurag University", "07:20 AM", "04:30 PM", "Habsiguda, Ramanthapur, Uppal Ring Road, Medipally, Campus", "19 km", "38 mins", 1),
                    new Route("R-118", "Amberpet to Anurag University", "Amberpet 6 No", "Anurag University", "07:15 AM", "04:30 PM", "Amberpet, Shivam Road, Golnaka, Uppal Depot, Ghatkesar, Campus", "22 km", "42 mins", 1),
                    new Route("R-119", "Patancheru to Anurag University", "Patancheru Bus Stand", "Anurag University", "06:25 AM", "04:30 PM", "Patancheru, BHEL, Miyapur, Kukatpally, Tarnaka, Campus", "48 km", "80 mins", 1),
                    new Route("R-120", "Shamshabad to Anurag University", "Shamshabad Bus Stop", "Anurag University", "06:30 AM", "04:30 PM", "Shamshabad, Aramghar, Chandrayangutta, LB Nagar, ORR, Campus", "44 km", "65 mins", 1)
                );
                routeRepository.saveAll(routes);

                // 20 Buses for Anurag University
                List<Bus> buses = List.of(
                    new Bus("BUS-01", "ECIL to Anurag University", "Ramesh Kumar", "+91 98480 11001", "Radhika Junction", "Running", 17.4850, 78.5601, 52, 46, "42 km/h", "08:15 AM", "Kushaiguda"),
                    new Bus("BUS-02", "Secunderabad to Anurag University", "Suresh Naidu", "+91 98480 11002", "Tarnaka Flyover", "Running", 17.4284, 78.5286, 54, 50, "38 km/h", "08:20 AM", "Habsiguda"),
                    new Bus("BUS-03", "Dilsukhnagar to Anurag University", "Venkatesh G", "+91 98480 11003", "LB Nagar Circle", "Delayed", 17.3457, 78.5522, 50, 48, "24 km/h", "08:35 AM", "Nagole"),
                    new Bus("BUS-04", "Keesara to Anurag University", "K. Prabhakar", "+91 98480 11004", "Bogaram Village", "Running", 17.5186, 78.6812, 52, 38, "45 km/h", "08:10 AM", "Rampally"),
                    new Bus("BUS-05", "Uppal Depot to Anurag University", "Mohd. Ismail", "+91 98480 11005", "Medipally Arch", "Running", 17.4125, 78.5874, 50, 44, "46 km/h", "08:05 AM", "Narapally"),
                    new Bus("BUS-06", "Mehdipatnam to Anurag University", "A. Satyanarayana", "+91 98480 11006", "Amberpet 6 No", "Running", 17.3912, 78.5178, 55, 52, "35 km/h", "08:25 AM", "Uppal"),
                    new Bus("BUS-07", "Kukatpally to Anurag University", "G. Narsimha", "+91 98480 11007", "Bowenpally Checkpost", "Delayed", 17.4721, 78.4832, 55, 53, "28 km/h", "08:40 AM", "Tarnaka"),
                    new Bus("BUS-08", "Miyapur to Anurag University", "B. Chandraiah", "+91 98480 11008", "Begumpet Flyover", "Running", 17.4448, 78.4682, 52, 45, "40 km/h", "08:30 AM", "Mettuguda"),
                    new Bus("BUS-09", "LB Nagar to Anurag University", "Ch. Srinivas", "+91 98480 11009", "Hayathnagar Depot", "Running", 17.3241, 78.6012, 50, 42, "48 km/h", "08:12 AM", "Pedda Amberpet"),
                    new Bus("BUS-10", "Malkajgiri to Anurag University", "M. Yadagiri", "+91 98480 11010", "Neredmet X Roads", "Running", 17.4789, 78.5367, 50, 47, "36 km/h", "08:18 AM", "ECIL"),
                    new Bus("BUS-11", "Charminar to Anurag University", "Syed Farooq", "+91 98480 11011", "Santoshnagar Phisalbanda", "Running", 17.3489, 78.5112, 52, 49, "34 km/h", "08:28 AM", "LB Nagar"),
                    new Bus("BUS-12", "Banjara Hills to Anurag University", "D. Mallesh", "+91 98480 11012", "RTC X Roads", "Running", 17.4089, 78.4981, 52, 48, "36 km/h", "08:26 AM", "Uppal"),
                    new Bus("BUS-13", "Lingampally to Anurag University", "P. Anjaiah", "+91 98480 11013", "ORR Exit 8", "Running", 17.4412, 78.5912, 50, 46, "55 km/h", "08:08 AM", "Ghatkesar"),
                    new Bus("BUS-14", "Alwal to Anurag University", "K. Jagan", "+91 98480 11014", "Yapral Post", "Running", 17.5012, 78.5412, 50, 44, "38 km/h", "08:20 AM", "Cherlapally"),
                    new Bus("BUS-15", "Medchal to Anurag University", "R. Krishna", "+91 98480 11015", "Kompally Junction", "Delayed", 17.5389, 78.4891, 55, 51, "26 km/h", "08:42 AM", "Suchitra"),
                    new Bus("BUS-16", "Nagole to Anurag University", "T. Mahesh", "+91 98480 11016", "Boduppal Kaman", "Running", 17.4112, 78.5712, 50, 39, "44 km/h", "08:08 AM", "Narapally"),
                    new Bus("BUS-17", "Habsiguda to Anurag University", "V. Ravi Kumar", "+91 98480 11017", "Ramanthapur Church", "Running", 17.3989, 78.5389, 50, 45, "42 km/h", "08:14 AM", "Uppal Ring Road"),
                    new Bus("BUS-18", "Amberpet to Anurag University", "S. Venkat", "+91 98480 11018", "Golnaka Bridge", "Running", 17.3891, 78.5145, 50, 43, "39 km/h", "08:22 AM", "Uppal"),
                    new Bus("BUS-19", "Patancheru to Anurag University", "N. Shankar", "+91 98480 11019", "Miyapur Metro", "Running", 17.4989, 78.3612, 55, 52, "46 km/h", "08:35 AM", "Kukatpally"),
                    new Bus("BUS-20", "Shamshabad to Anurag University", "J. Naresh", "+91 98480 11020", "Aramghar X Roads", "Running", 17.3212, 78.4412, 52, 47, "45 km/h", "08:30 AM", "Chandrayangutta")
                );
                busRepository.saveAll(buses);

                // Students with different student IDs and passwords assigned to different routes
                List<Student> students = List.of(
                    new Student("AU21001", "Rahul Sharma", "pass123", "rahul.au21@anurag.edu.in", "CSE", "BUS-01", "ECIL to Anurag University", "Radhika Junction", "+91 98490 00101"),
                    new Student("AU21002", "Priya Reddy", "pass123", "priya.au21@anurag.edu.in", "ECE", "BUS-02", "Secunderabad to Anurag University", "Tarnaka Flyover", "+91 98490 00102"),
                    new Student("AU21003", "Sai Teja", "pass123", "saiteja.au21@anurag.edu.in", "AI&ML", "BUS-03", "Dilsukhnagar to Anurag University", "LB Nagar Circle", "+91 98490 00103"),
                    new Student("AU21004", "Ananya Rao", "pass123", "ananya.au21@anurag.edu.in", "IT", "BUS-04", "Keesara to Anurag University", "Bogaram Village", "+91 98490 00104"),
                    new Student("AU21005", "Mohammed Zaid", "pass123", "zaid.au21@anurag.edu.in", "MECH", "BUS-05", "Uppal Depot to Anurag University", "Medipally Arch", "+91 98490 00105"),
                    new Student("AU21006", "Sneha Kulkarni", "pass123", "sneha.au21@anurag.edu.in", "CIVIL", "BUS-07", "Kukatpally to Anurag University", "Bowenpally Checkpost", "+91 98490 00106"),
                    new Student("AU21007", "Karthik Varma", "pass123", "karthik.au21@anurag.edu.in", "CSE", "BUS-08", "Miyapur to Anurag University", "Begumpet Flyover", "+91 98490 00107"),
                    new Student("AU21008", "Divya Sri", "pass123", "divya.au21@anurag.edu.in", "DATA SCIENCE", "BUS-16", "Nagole to Anurag University", "Boduppal Kaman", "+91 98490 00108"),
                    new Student("AU21009", "Arjun Reddy", "pass123", "arjun.au21@anurag.edu.in", "ECE", "BUS-13", "Lingampally to Anurag University", "ORR Exit 8", "+91 98490 00109"),
                    new Student("AU21010", "Meghana P", "pass123", "meghana.au21@anurag.edu.in", "CSE", "BUS-17", "Habsiguda to Anurag University", "Habsiguda Circle", "+91 98490 00110")
                );
                studentRepository.saveAll(students);
            }
        };
    }
}
