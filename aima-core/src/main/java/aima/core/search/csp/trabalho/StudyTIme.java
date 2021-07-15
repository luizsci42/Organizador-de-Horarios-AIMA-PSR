package aima.core.search.csp.trabalho;

class StudyTIme extends  Atividade {
    final Disciplina disciplina;
    final int numberBlock;

    StudyTIme(Disciplina disciplina,  int numberBlock) {
        this.disciplina = disciplina;
        this.numberBlock = numberBlock;
    }

    @Override
    public String toString() {
        return "Study to " + this.disciplina.getCodigo();
    }
}
