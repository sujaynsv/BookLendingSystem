package com.sujay.booklending.service;

import com.sujay.booklending.model.Book;
import com.sujay.booklending.model.LendingRecord;
import com.sujay.booklending.model.Student;
import com.sujay.booklending.repository.BookRepository;
import com.sujay.booklending.repository.LendingRepository;
import com.sujay.booklending.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LendingService {

    private final LendingRepository LendingRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    public LendingService(LendingRepository LendingRepository,
                          BookRepository bookRepository,
                          StudentRepository studentRepository) {
        this.LendingRepository = LendingRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public List<LendingRecord> getAllRecords() {
        return LendingRepository.findAll();
    }

    public boolean lendBook(Long bookId, Long studentId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (bookOpt.isPresent() && studentOpt.isPresent()) {
            Book book = bookOpt.get();
            if (!book.isAvailable()) {
                return false; // Book already lent
            }

            Student student = studentOpt.get();
            LendingRecord record = new LendingRecord(book, student, LocalDate.now());
            LendingRepository.save(record);

            book.setAvailable(false);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(Long recordId) {
        Optional<LendingRecord> recordOpt = LendingRepository.findById(recordId);
        if (recordOpt.isPresent()) {
            LendingRecord record = recordOpt.get();
            record.setReturnDate(LocalDate.now());
            LendingRepository.save(record);

            Book book = record.getBook();
            book.setAvailable(true);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    public Optional<LendingRecord> getRecordById(Long id) {
        return LendingRepository.findById(id);
    }
}
