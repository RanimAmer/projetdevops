<<<<<<< HEAD
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> retrieveAllEtudiants();
    public Etudiant retrieveEtudiant(Long etudiantId);
    public Etudiant addEtudiant(Etudiant c);
    public void removeEtudiant(Long etudiantId);
    public Etudiant modifyEtudiant(Etudiant etudiant);
    public Etudiant recupererEtudiantParCin(long cin);


}
=======
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {

    public List<Etudiant> retrieveAllEtudiants();
    public Etudiant retrieveEtudiant(Long etudiantId);
    public Etudiant addEtudiant(Etudiant c);
    public void removeEtudiant(Long etudiantId);
    public Etudiant modifyEtudiant(Etudiant etudiant);
    public Etudiant recupererEtudiantParCin(long cin);


}
>>>>>>> 08d8cc2 (Initial commit)
