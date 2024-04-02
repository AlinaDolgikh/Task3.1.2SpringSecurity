package ru.itmentor.spring.boot_security.demo.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.models.Role;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Set<Role> getRoleByName(String[] roleName) {
        Set<Role> roles = new HashSet<>();
        for (String role : roleName) {
            roles.add(roleRepository.findRoleByName(role));
        }
        return roles;
    }
}

/*Метод getRoleByName() в репозитории UserRepository используется для извлечения набора ролей по их именам.
Он принимает массив имен ролей в качестве параметра и возвращает набор объектов Role.
Создается новый пустой набор roles типа HashSet.
Метод перебирает массив имен ролей.
Для каждого имени роли вызывается метод findRoleByName() репозитория RoleRepository для извлечения соответствующей роли из базы данных.
Извлеченная роль добавляется в набор roles.
Наконец, набор roles возвращается.
Этот метод полезен, когда вам нужно извлечь несколько ролей по их именам и назначить их пользователю или группе.
Например, следующий код использует метод getRoleByName() для извлечения ролей "ROLE_USER" и "ROLE_ADMIN" и назначения их пользователю:
Set<Role> roles = userRepository.getRoleByName(new String[] {"ROLE_USER", "ROLE_ADMIN"});
user.setRoles(roles);
В этом примере набор roles будет содержать две роли: объект Role с именем "ROLE_USER" и объект Role с именем "ROLE_ADMIN".
Затем эти роли назначаются пользователю с помощью метода setRoles().
 */