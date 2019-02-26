package service;

import domain.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LikeRepository;

import java.util.List;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public void save(Like like) {
        likeRepository.save(like);
    }

    @Override
    public Like findOneById(Long id) {
        return likeRepository.getOne(id);
    }

    @Override
    public void delete(long id) {
        likeRepository.deleteById(id);
    }
}
