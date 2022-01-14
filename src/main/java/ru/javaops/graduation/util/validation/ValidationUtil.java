package ru.javaops.graduation.util.validation;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import ru.javaops.graduation.HasId;
import ru.javaops.graduation.error.DuplicateUsersVoteException;
import ru.javaops.graduation.error.IllegalRequestDataException;
import ru.javaops.graduation.error.VoteDateTimeException;
import ru.javaops.graduation.model.Vote;
import ru.javaops.graduation.repository.VoteRepository;
import ru.javaops.graduation.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new IllegalRequestDataException("Entity with id=" + id + " not found");
        }
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static void checkVoteDateTime(Vote vote) {
        if(vote.getUserId() == SecurityUtil.authId() && vote.getVoteDate().isEqual(LocalDate.now()) && LocalDateTime.now().getHour()>11){
            throw new VoteDateTimeException("It's too late, vote can't be changed. You can vote again tomorrow");
        }
    }
}