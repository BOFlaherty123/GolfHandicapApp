package test.java.co.uk.myhandicap.dao;

import main.java.co.uk.myhandicap.dao.HoleDaoImpl;
import main.java.co.uk.myhandicap.model.handicap.Hole;
import main.java.co.uk.myhandicap.model.user.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Hole Dao Implementation Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 29/09/2014
 * @project MyHandicapApp
 */
@RunWith(MockitoJUnitRunner.class)
public class HoleDaoImplTest {

    @InjectMocks
    private HoleDaoImpl holeDaoImpl = new HoleDaoImpl();

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private User user;

    @Mock
    private Query query;

    @Mock
    private Transaction transaction;

    @Before
    public void setup() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void retrieveAllHolesWithMatchingParIsValid() {
        when(session.createQuery(anyString())).thenReturn(query);

        List<Hole> holeList = new ArrayList();
        holeList.add(new Hole());

        when(query.list()).thenReturn(holeList);

        List<Hole> result = holeDaoImpl.retrieveHoleAverageByHolePar(user, "3");
        verify(session, times(1)).beginTransaction();
        verify(session.getTransaction(), times(1)).commit();
        assertThat(result.size(), is(1));

    }

    @Test
    public void retrieveAllHolesWithMatchingYardageIsValid() {

        when(session.createQuery(anyString())).thenReturn(query);

        List<Hole> holeList = new ArrayList();
        holeList.add(new Hole());

        when(query.list()).thenReturn(holeList);

        List<Hole> result = holeDaoImpl.retrieveHoleAverageByHoleYardage(user, "172");
        verify(session, times(1)).beginTransaction();
        verify(session.getTransaction(), times(1)).commit();
        assertThat(result.size(), is(1));

    }

}