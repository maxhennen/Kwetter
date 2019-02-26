package service;

import domain.Kweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.KweetRepository;

import java.util.List;

@Service
@Transactional
public class KweetServiceImpl implements KweetService{

    @Autowired
    private KweetRepository kweetRepository;

    @Override
    public List<Kweet> findAll() {
        return kweetRepository.findAll();
    }

    @Override
    public void save(Kweet kweet) {
        kweetRepository.save(kweet);
    }

    @Override
    public Kweet findOneById(long id) {
        return kweetRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        kweetRepository.deleteById(id);
    }

    @Override
    public List<Kweet> findByUserUsername(String username) {
        return kweetRepository.findByUserUsername(username);
    }
}
