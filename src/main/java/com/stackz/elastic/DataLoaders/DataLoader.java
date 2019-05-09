package com.stackz.elastic.DataLoaders;

import com.stackz.elastic.model.Doctor;
import com.stackz.elastic.repository.DoctorMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader {


    private final @NotNull DoctorMongoRepository doctorMongoRepository;


    public void run(ApplicationArguments applicationArguments) throws Exception {
        loadMongoData();
    }

    public void loadMongoData() {
        log.info("................... Loading Doctor in Mongo Respository .............................................");
        doctorMongoRepository.deleteAll();
        getDoctorData().forEach(doctorMongoRepository::save);
        log.info("................... Loaded Doctor in Mongo Respository .............................................");
    }

    public List<Doctor> getDoctorData() {
        List<Doctor> doctorList = new ArrayList<>();
        try{
        File file = ResourceUtils.getFile("classpath:names.csv");
        Path path = file.toPath();
        Long i = 0L;
        boolean firstRow = true;

            BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String line = bufferedReader.readLine();
            while (line != null) {
                if(!firstRow) {
                    i = i + 1;
                    String [] attributes = line.split(",");
                    doctorList.add(createDoctor(attributes[1], i));
                }
                line = bufferedReader.readLine();
                firstRow = false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Exception while loading doctor data");
        }
        log.info("Doctor data Loaded from file....... [ ok ]");
        return doctorList;
    }

    public Doctor createDoctor(String name, Long id) {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name.replace("\"", ""));
        doctor.setType(DoctorType.randomType().name());
        doctor.setQualification(DoctorQualif.randomType().name());
        return doctor;
    }

    public enum DoctorType {
        SURGEON,
        OPD,
        EYE_SPECIALIST,
        ENT_SPECIALIST,
        HEART_SURGEON,
        BRAIN_SURGEON,
        ORTHO_SPECIALIST;

        private static final List<DoctorType> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static DoctorType randomType()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public enum DoctorQualif {
        MBBS,
        FRCS;

        private static final List<DoctorQualif> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static DoctorQualif randomType()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }
}
