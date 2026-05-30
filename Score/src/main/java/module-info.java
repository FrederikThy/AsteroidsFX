import dk.sdu.cbse.common.services.IPostProcessingService;

module Score {
    requires Common;
    requires spring.web;

    provides IPostProcessingService with dk.sdu.cbse.score.ScoringSystem;
}