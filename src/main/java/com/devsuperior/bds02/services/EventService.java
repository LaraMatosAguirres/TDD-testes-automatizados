package com.devsuperior.bds02.services;


import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceDataBaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public List<EventDTO> findAll(){
        List<Event> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public EventDTO insert(EventDTO dto){
        Event event = new Event();
        copyDTOToEntity(dto, event);
        event = repository.save(event);
        return  new EventDTO(event);
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event event = repository.getOne(id);
            copyDTOToEntity(dto, event);
            event = repository.save(event);
            return new EventDTO(event);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Evento não encontrado: " + id);
        }
    }


    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Evento não encontrado: " + id);
        }catch (DataIntegrityViolationException e) {
            throw new ResourceDataBaseException("O Evento" + id + "não pode ser deletado");
        }
    }

    private void copyDTOToEntity(EventDTO dto, Event event){

        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        City city = cityRepository.getOne(dto.getCityId());
        event.setCity(city);
    }


}