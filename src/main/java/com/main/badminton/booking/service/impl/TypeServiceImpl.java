package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.TypeConverter;
import com.main.badminton.booking.dto.request.TypeRequestDTO;
import com.main.badminton.booking.dto.response.TypeResponseDTO;
import com.main.badminton.booking.entity.Types;
import com.main.badminton.booking.repository.TypeRepository;
import com.main.badminton.booking.service.interfc.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private final TypeRepository typeRepository;
    @Autowired
    private final TypeConverter typeConverter;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository, TypeConverter typeConverter) {
        this.typeRepository = typeRepository;
        this.typeConverter = typeConverter;
    }

    @Override
    public void createType(TypeRequestDTO typeRequestDTO) {
        Types type = typeConverter.ToEntity(typeRequestDTO);
        typeRepository.save(type);
    }

    @Override
    public TypeResponseDTO updateType(Integer id, TypeRequestDTO typeRequestDTO) {
        var type = typeRepository.findById(id);
        if (type.isPresent()) {
            Types typeUpdate = typeConverter.ToEntity(typeRequestDTO);
            typeUpdate.setId(id);
            typeRepository.save(typeUpdate);
            return typeConverter.ToResponseDTO(typeUpdate);
        } else {
            throw new RuntimeException("Not found any type with this id");
        }
    }

    @Override
    public List<TypeResponseDTO> getAllTypes() {
        return typeRepository
                .findAll()
                .stream()
                .map(typeConverter::ToResponseDTO)
                .toList();
    }
}
