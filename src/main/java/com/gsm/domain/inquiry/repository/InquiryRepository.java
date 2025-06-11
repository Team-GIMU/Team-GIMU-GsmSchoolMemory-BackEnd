package com.gsm.domain.inquiry.repository;

import com.gsm.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
